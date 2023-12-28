package br.com.optimate.manager.service;

import br.com.optimate.manager.config.UtilsRandom;
import br.com.optimate.manager.domain.user.Avatar;
import br.com.optimate.manager.domain.user.User;
import br.com.optimate.manager.repository.AvatarRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@ApplicationScoped
public class AvatarService implements AbstractService {

    AvatarRepository avatarRepository;

    @Inject
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public Avatar createAvatarDefault(User user) {
        Avatar avatar = user.getAvatar();
        if (Optional.ofNullable(avatar).isEmpty()) {
            avatar = new Avatar(user);
        }
        String color = UtilsRandom.generateColorHex();
        avatar.setAvatar220(getAvatarImageDefault(user.getPersonalInformation().getFullName(), 220, color));
        avatar.setAvatar70(getAvatarImageDefault(user.getPersonalInformation().getFullName(), 70, color));
        avatar.setAvatar35(getAvatarImageDefault(user.getPersonalInformation().getFullName(), 35, color));
        avatarRepository.persist(avatar);
        return user.getAvatar();
    }

    private String getAvatarImageDefault(String fullName, int size, String color) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            String params = "name=".concat(fullName.replace(" ", "+"));
            params += "&size=" + size;
            params += "&background=" + color;
            HttpGet request = new HttpGet("https://ui-avatars.com/api/?" + params);
            request.setHeader("Content-Type", "application/json");
            HttpResponse httpresponse = httpClient.execute(request);
            InputStream content = httpresponse.getEntity().getContent();


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int len = 0;
            try {
                while ((len = content.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String encodedImage = new String(Base64.encodeBase64(baos.toByteArray()), StandardCharsets.UTF_8);
            return "data:image/png;base64," + encodedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

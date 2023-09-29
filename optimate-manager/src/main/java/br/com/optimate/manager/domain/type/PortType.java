package br.com.optimate.manager.domain.type;

public enum PortType {
    PUBLIC_PIER("Cais público"),
    TERMINAL_PRIVATE("Terminal de uso privado"),
    SUPPORT_INSTALLATION("Instalação de apoio"),
    SHIPYARD("Estaleiro"),
    MISCELLANEOUS("Diversas"),
    TRANSFER_STATION("Estação de transbordo de carga");

    private final String name;

    PortType(String name) { this.name = name; }
}

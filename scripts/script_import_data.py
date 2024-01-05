import pandas as pd
from sqlalchemy import create_engine, text


# Import files
dir_country = pd.read_excel("../import-files/paises.xls")
dir_shipOwner = pd.read_excel("../import-files/armador.xlsx") 
dir_area = pd.read_excel("../import-files/area.xlsx")
dir_porto = pd.read_excel("../import-files/portos.xlsx")
dir_port_facility = pd.read_excel("../import-files/instalacao_portuaria.xlsx")
dir_berth = pd.read_excel("../import-files/bercos_para.xlsx")
dir_product_group = pd.read_excel("../import-files/grupo_mercadoria.xlsx")
dir_product = pd.read_excel("../import-files/mercadoria.xlsx")

#Load columns
tb_country = pd.DataFrame(dir_country, columns=["id", "name", "cod", "status"])
tb_shipOwner = pd.DataFrame(dir_shipOwner, columns=["id", "cod", "country_id", "nickname", "name", "is_active"])
tb_porto = pd.DataFrame(dir_porto, columns=["id", "bigram", "trigram", "name", "country_id"])
tb_port_facility = pd.DataFrame(dir_port_facility, columns=["id", "acronym_port", "acronym_port_antaq", "name", "is_active", "port_type"])
tb_area = pd.DataFrame(dir_area, columns=["id", "acronym", "acronym_mooring", "description", "port_facility_id"])
tb_berth = pd.DataFrame(dir_berth, columns=["id", "acronym_berth", "acronym_berth_antaq", "name", "mooring_location_id"])
tb_product_group = pd.DataFrame(dir_product_group, columns=["id", "acronym", "description"])
tb_product = pd.DataFrame(dir_product, columns=["id", "acronym", "description", "product_group_id","is_active"])

# Connection DB
engine = create_engine("postgresql://postgres:postgres@localhost:5432/optimate-db")
tb_country.to_sql("country", engine, if_exists="append", index=False)
tb_shipOwner.to_sql("ship_owner", engine, if_exists="append", index=False)
tb_porto.to_sql("port", engine, if_exists="append", index=False)
tb_port_facility.to_sql("port_facility", engine, if_exists="append", index=False)
tb_area.to_sql("mooring_location", engine, if_exists="append", index=False)
tb_berth.to_sql("berth", engine, if_exists="append", index=False)
tb_product_group.to_sql("product_group", engine, if_exists="append", index=False)
tb_product.to_sql("product", engine, if_exists="append", index=False)

# Create admin user
with engine.begin() as connection:
  connection.execute(text("INSERT INTO optimate_user (receive_emails, status, id, password_hash, username) VALUES (false, 2, 1, 'password', 'admin')"))
  connection.execute(text("INSERT INTO user_roles(id, role) VALUES(1, 'admin')"))

engine.dispose()

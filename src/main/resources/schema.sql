CREATE TABLE TB_QUARTO( 
					   NUMERO VARCHAR(5) NOT NULL, 
					   TAMANHO NUMERIC(9,2), 
					   TIPO VARCHAR(10), 
					   OPCIONAIS VARCHAR(200), 
					   N_CAMAS INTEGER NOT NULL, 
					   VALOR NUMERIC(12,2) NOT NULL, 
					   PRIMARY KEY(NUMERO));
					   
CREATE TABLE TB_PESSOA(ID SERIAL, 
					   NOME VARCHAR(50) NOT NULL, 
					   CPF VARCHAR(11) NOT NULL,
					   EMAIL VARCHAR(30) NOT NULL,
					   SENHA VARCHAR(16) NOT NULL,
					   TELEFONE VARCHAR(13) NOT NULL,  
					   PRIMARY KEY(ID));
					   
CREATE TABLE TB_RESERVA(ID SERIAL, 
					   
					   DATA_ENTRADA TIMESTAMP NOT NULL,
					   DATA_SAIDA TIMESTAMP NOT NULL,
					   fk_pessoa SERIAL REFERENCES TB_PESSOA(ID) NOT NULL,
						fk_quarto VARCHAR(5) REFERENCES TB_QUARTO(NUMERO) NOT NULL,
					   PRIMARY KEY(ID));
					   
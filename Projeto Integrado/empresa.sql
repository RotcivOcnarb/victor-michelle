
CREATE SCHEMA IF NOT EXISTS `empresa` DEFAULT CHARACTER SET utf8;
USE empresa ;

-- -----------------------------------------------------
-- Table `mydb`.`Empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Empresa (
    idEmpresa INT NOT NULL AUTO_INCREMENT,
    cnpj VARCHAR(14) NOT NULL,
    razaoSocial VARCHAR(45) NOT NULL,
    horaAbertura VARCHAR(5) NOT NULL,
    horaFechamento VARCHAR(5) NOT NULL,
    temperaturaAr VARCHAR(2) NOT NULL,
    PRIMARY KEY (idEmpresa),
    UNIQUE INDEX cnpj_UNIQUE (cnpj ASC)
);


CREATE TABLE IF NOT EXISTS Conjunto (
    idConjunto INT NOT NULL AUTO_INCREMENT,
    ocupado boolean NOT NULL,

	EmpresaCnpj VARCHAR(14),
    PRIMARY KEY (idConjunto),
    UNIQUE INDEX idConjunto_UNIQUE (idConjunto ASC),
    INDEX fk_Conjunto_Empresa_cnpjx (EmpresaCnpj ASC),
    CONSTRAINT fk_Conjunto_Empresa FOREIGN KEY (EmpresaCnpj)
        REFERENCES Empresa (cnpj)
);



CREATE TABLE IF NOT EXISTS Pessoa (
    idPessoa INT NOT NULL AUTO_INCREMENT,
    nome varchar(40) not null,
    perfil smallint NOT NULL,
    cpf varchar(11) NOT NULL,
    senha varchar(100) NOT NULL,
    entradaMax varchar(5) NOT NULL,
    entradaMin varchar(5) NOT NULL,
    acesso boolean not null,
	EmpresaCnpj VARCHAR(14) NOT NULL,
    PRIMARY KEY (idPessoa),
    UNIQUE INDEX cpf_UNIQUE (cpf ASC),
    INDEX fk_Pessoa_Empresa_cnpjx (EmpresaCnpj ASC),
    CONSTRAINT fk_Pessoa_Empresa FOREIGN KEY (EmpresaCnpj)
        REFERENCES Empresa (cnpj)
);

Create table if not exists Acesso(
	saiu boolean default false,
	idAcesso int not null auto_increment,
	horaEntrada smallint not null,
	minutoEntrada smallint not null,
	diaEntrada smallint not null,
	mesEntrada smallint not null,
	anoEntrada smallint not null,
	horaSaida smallint,
	minutoSaida smallint,
	diaSaida smallint,
	mesSaida smallint,
	anoSaida smallint,
	idPessoa int not null,

	PRIMARY KEY (idAcesso),
    INDEX fk_Acesso_Pessoa_idPessoax (idPessoa ASC),
    CONSTRAINT fk_Acesso_Pessoa FOREIGN KEY (idPessoa)
        REFERENCES Pessoa (idPessoa)
	

);






insert into Conjunto (ocupado)values(
false),(false),(false),(false),
(false),(false),(false),(false),
(false),(false),(false),(false
);


insert into Empresa(cnpj,razaoSocial,horaAbertura,horaFechamento,temperaturaAr) values(
'12345678922222','Administradora',08.30,17.30,21);

insert into Pessoa(nome,perfil,cpf,senha,entradaMin,entradaMax,acesso,EmpresaCnpj ) values(
'Adm',1,'01234567890',"508722-47116-110-7-10034883468114109-108",'10:00','18:00',true,'12345678922222'
);




/*
insert into Empresa(cnpj,razaoSocial,horaAbertura,horaFechamento,temperaturaAr) values(
'12345678922222','',08.30,17.30,21

);
insert into Empresa(cnpj,razaoSocial,horaAbertura,horaFechamento,temperaturaAr) values('123456789abcd1','Empresa Teste','8.3','17.3','21');

UPDATE Conjunto
    SET ocupado = false,
        EmpresaCnpj = null
    WHERE idConjunto>0;

select * from Conjunto;

UPDATE Conjunto SET ocupado = true,EmpresaCnpj = '123456789abcde' WHERE idConjunto=1;
UPDATE Conjunto SET ocupado = true,EmpresaCnpj = '23456789abcde1' WHERE idConjunto=2;
UPDATE Conjunto SET ocupado = true,EmpresaCnpj = '3456789abcde12' WHERE idConjunto=3;
UPDATE Conjunto SET ocupado = true,EmpresaCnpj = '456789abcde123' WHERE idConjunto=4;



('Gabriel',1,'123456789ab',1234,'10:00','18:00',true,'123456789abcde'),
('Matheus',0,'23456789abc',1234,'10:00','18:00',true,'123456789abcde'
);

insert into Pessoa(nome,perfil,cpf,senha,entradaMax,entradaMin,acesso,EmpresaCnpj ) values(
'Eu',5,'012345vcc9o',
'123451706468964964-9379-6121-58-104-59-11268','10:00','18:00',true,'123456789abcde');


update Pessoa set nome='arthur',perfil=2,cpf='rfghnm',entradaMax='10:00',entradaMin='10:00',acesso=1,EmpresaCnpj='123456789abcde' where idPessoa=6;


select * from Acesso where idPessoa =6 order by idAcesso desc;
select * from acesso;

UPDATE Acesso
    SET saiu = false,horaSaida=null,minutoSaida=null,mesSaida=null,anoSaida=null
    WHERE idAcesso=7;



select * from pessoa;



select idPessoa,senha,perfil,entradaMin,entradaMax,acesso from Pessoa;

select idPessoa from Pessoa order by idPessoa desc;

select *from  Acesso where idAcesso=1;

select count(*) from acesso;


SELECT nome,cpf,EmpresaCnpj
FROM pessoa
INNER JOIN acesso
ON pessoa.idPessoa=acesso.idPessoa;

SELECT acesso.*, pessoa.nome, pessoa.cpf , pessoa.empresaCnpj FROM acesso JOIN pessoa USING(idPessoa) order by pessoa.empresaCnpj;


/*//1 sindico ; 2 atendente ; 3 funcionario ; 0 não entra no sistema ; 4 Administrador*/

SELECT acesso.*, pessoa.nome, pessoa.cpf , pessoa.empresaCnpj, pessoa.perfil from acesso JOIN pessoa USING(idPessoa) where pessoa.perfil=1 or pessoa.perfil=2
 order by empresaCnpj, mesSaida desc ,diaSaida desc,horaSaida desc,minutoSaida desc;


SELECT acesso.*, pessoa.nome, pessoa.cpf , pessoa.empresaCnpj FROM acesso JOIN pessoa USING(idPessoa) where diaSaida=16 and mesSaida=9 and anoSaida=2016 order by empresaCnpj, mesSaida desc ,diaSaida desc,horaSaida desc,minutoSaida desc ;

select  conjunto.* ,empresa.temperaturaAr From conjunto join empresa on Conjunto.EmpresaCnpj = empresa.cnpj;
select  conjunto.idConjunto ,conjunto.empresaCnpj,empresa.temperaturaAr From conjunto join empresa on Conjunto.EmpresaCnpj = empresa.cnpj;



select  idAcesso from Acesso order by idAcesso desc limit 1;

select * from acesso;

select 'book' as item_type, * from book where mid = 4
union all
select 'journal' as item_type, * from journal where mid = 4
union all
select 'conference' as item_type, * from conference where mid = 4





UPDATE Empresa SET cnpj =? ,razaoSocial= ?,horaAbertura=? ,horaFechamento=? ,temperaturaAr=? where idEmpresa=?;

delete from Empresa where idEmpresa=5;

select * from Empresa;

delete from Conjunto where idConjunto>0;

delete from Acesso where idPessoa>0;

select cnpj from empresa where razaoSocial ='Empresa Teste';


select nome,perfil,cpf,entradaMin,entradaMax,acesso,EmpresaCnpj  from pessoa;

insert into Acesso(horaEntrada, minutoEntrada, diaEntrada, mesEntrada, anoEntrada,idPessoa) values (
10,10,15,9,2016,0
);

select * from Conjunto;

SELECT acesso.*, pessoa.nome, pessoa.cpf , pessoa.empresaCnpj FROM acesso JOIN pessoa USING(idPessoa) where idAcesso =25 order by mesSaida desc ,diaSaida desc,horaSaida desc,minutoSaida desc;


SELECT * FROM Empresa where idEmpresa = 8;
*/

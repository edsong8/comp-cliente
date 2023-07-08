select 	id ,empresa_id ,grupo as grupo_empresa_id ,tipo_pessoa ,destinatario,inativo ,nao_contribuinte , tipoinscr as tipo_inscricao , 
		cnpj as cpf_cnpj  ,inscricao  ,tipo as tipo_atividade , replace(nome, '&','') as nome ,  replace(apelido, '&','') as  apelido ,cep ,ender, nr ,compl ,bairro ,cidade ,estado ,cpais as c_pais , 
		pais x_pais , codpostal as cod_postal ,telefone ,email ,pgto as forma_pgto ,promob ,markup ,frete ,montagem ,layout as layout_projeto , contapgant as  conta_pg_ant ,  
		contapgfat as  conta_pg_fat , contapgped as  conta_pg_ped ,access_token_gabster , current_timestamp as data_inclusao
	from fornecedor 
	where grupo is not null
	order by id
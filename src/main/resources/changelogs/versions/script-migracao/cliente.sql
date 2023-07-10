select 	id,empresa_id, null as contato_Id, tipo_pessoa, nao_contribuinte, replace(cpf_cnpj,'.','')  cpf_cnpj, replace(nome,'&','') as nome, 
		rg as rg_ie, data_nascimento, cep , logradouro as  rua, nr, replace(complemento,'&','') as complemento, bairro, cidade, estado,
		1058 as c_pais,'BRASIL' as x_pais, null as cod_postal, cep_entrega, logradouro_entrega as rua_entrega, nr_entrega, 
		 replace(complemento_entrega,'&','') as complemento_entrega, bairro_entrega, 
		cidade_entrega, estado_entrega, endereco_entrega_igual, dt_cadastro as data_cadastro, venda_simplificada
from cliente
order by id

# lp1-2023-projeto5
Projeto Sistema de Controle de Máquinas de Alimentos
## [Link do Projeto](https://docs.google.com/document/d/1HNgsj6d2ofMJocTMxAGiWbGbcBiL7pEV1Su-kRpeT3k/edit#heading=h.gjdgxs)

## Como usar o Git

Faça um clone do repositório
```bash
git clone https://github.com/MatheusViniciusV/lp1-2023-projeto5.git
```

Crie uma branch para cada caso de uso, use o mesmo como nome o código do caso de uso (ex. GER CSU01).

```bash
git checkout -b {nome_da_branch}
```

Se a branch já existe mude para ela.

```bash
git checkout {nome_da_branch}
```

Ao terminar realize um add com o código criado.

```bash
git add *
```

Realize commit.

```bash
git commit -m "descrição do que foi feito"
```

E faça push na branch. Se ela ainda não está no github use:

```bash
git push --set-upstream origin {nome_da_branch}
```

Se ela já existe use:
```bash
git push origin {nome_da_branch}
```


Caso sua versão não esteja atualizada, use o seguinte comando para atualizar a branch local:

```bash
git pull origin {nome_da_branch_atualizada}
```

## Logar como gerente durante desenvolvimento

na tabela de gerente, salve a senha com o comando: 
``` sql
UPDATE `gerente` SET `senha` = 'F59EFF44A211FA33E2513758C4C3BB13582678C5FF8B64D6C6338A0C6512A4AD' WHERE `gerente`.`pk` = 1; 
```

ou só defina a senha como F59EFF44A211FA33E2513758C4C3BB13582678C5FF8B64D6C6338A0C6512A4AD (isso é `ger` após passar pelo hash). Na hora de logar use a senha ger.

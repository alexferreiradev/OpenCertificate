# Deploy the API
O deploy da api pode ser feita em qualquer ambiente que execute JAVA. Apos gerar o pacote JAR, basta executar na JVM. Contudo, atualmente o projeto utiliza o HEROKU para deploy 
com CI. 

## Deploy in STG

O deploy em STG com Heroku ocorre forçando as alterações da branch `main` para a branch `staging-api`. Esta branch é utilizada pela pipeline do Heroku para saber quando tem que 
gerar um novo POD e atualizar o atual. O heroku utiliza a raiz da branch para executar a criação do pod. Portanto, a branch não deve ter as pastas raiz do nosso repositório, 
somente a pasta backend como sendo a raiz da branch. Para fazer isso, utilize o seguinte comando na raiz do repositório:
```shell
git subtree push --prefix backend origin staging-api
```
Apos este commando, você estará alterando a branch `staging-api` com os novos commits que alteraram a pasta `backend`. Caso não funcione por merge, utilize: `push -f` que irá 
forçar a atualização da branch remota com o que você está enviando local. 

## Deploy in PRD

O deploy em PRD no Heroku não ocorre por branch e sim de forma sistemica. Logue no heroku, navegue até a pipeline de backend e clique no botão `promote to production`. Desta 
forma, a versão que está em staging será utilizada para gerar uma nova versão em PRD. Após este processo, o Heroku irá gerar um novo POD e quando este estiver ativo, irá 
desligar o antigo POD.

Temp mind map tree

temp tcdb
    Problema
        dores
            construção de ambiente grande para testes integracao
            muita configuração para rodar testes de integracao
            ambiente diferente para cada dev
            teste dependente de plataforma
            custo alto para ambiente de QA
            subir ambiente manualmente
            equipes com stack de tecnologias engessadas
                devido alto custo de montar diferentes arquiteturas de testes
        versao de plataformas
            diferentes versoes na equipe de devs
        configuracoes extras
            repassar script para cada dev
        custo para testar
            criar ambiente para pipeline de testes
                aumenta o custo homologacao
    publico
        dev junior
        dev pleno
    AIDA
        action
        des
        i
        A
    sessões do artigo
        intro
            relevancia para problema
                dados
            problema
                alto custo para ambiente de testes ou stg
                inflexibilidade de ambiente stg
                diferentes ambientes entre a equipe
            Intro de solucao
                Existencia de libraries
                docker
                    oq resolve
                test containers
                    oq resolve
        lib test containers
            funcionalidades
                data acess layer intergration tests
                    implantar DB para seus testes
                    manter mesma versao
                application integration tests
                    toda aplicação com dependencias
                    tempo de vida curto
                        somente o teste
                ui acceptances tests
                    teste de interfaces com containers de browsers como selenium
                much more
                    qualquer container que você criar
            background
                suportado
                    jUnit4
                    junit5
                    spock
        uso
            configuração
            examples de funcao
                local
                compartilhado
            ref ao artigo de como usar
        conclusao
            exemplos de outras libs similares
            limitações
    titulo
    tasks
        done
            criar branch
            criar post .md
            iniciar 5 minutes
            plan
                planejar problema
                decidir publico
                plan sections
            criar sections
            think about intro
            pesq conteudos
                done
                    blog que referencia custo
                        <https://www.servermania.com/kb/articles/how-much-does-a-server-cost-for-a-small-business/> <https://www.servermania.com/kb/articles/how-much-does-a-server-cost-for-a-small-business/>> <https://www.servermania.com/kb/articles/how-much-does-a-server-cost-for-a-small-business/>> <https://www.servermania.com/kb/articles/how-much-does-a-server-cost-for-a-small-business/>> <https://www.servermania.com/kb/articles/how-much-does-a-server-cost-for-a-small-business/>> <https://www.servermania.com/kb/articles/how-much-does-a-server-cost-for-a-small-business/>> <https://www.servermania.com/kb/articles/how-much-does-a-server-cost-for-a-small-business/>> <https://www.servermania.com/kb/articles/how-much-does-a-server-cost-for-a-small-business/>>
                    custo de servidor
                        <https://www.servermania.com/kb/articles/how-much-does-a-server-cost-for-a-small-business/> <https://www.servermania.com/kb/articles/how-much-does-a-server-cost-for-a-small-business/>> <https://www.servermania.com/kb/articles/how-much-does-a-server-cost-for-a-small-business/>> <https://www.servermania.com/kb/articles/how-much-does-a-server-cost-for-a-small-business/>> <https://www.servermania.com/kb/articles/how-much-does-a-server-cost-for-a-small-business/>> <https://www.servermania.com/kb/articles/how-much-does-a-server-cost-for-a-small-business/>> <https://www.servermania.com/kb/articles/how-much-does-a-server-cost-for-a-small-business/>> <https://www.servermania.com/kb/articles/how-much-does-a-server-cost-for-a-small-business/>>
                        list sites of servers
                            the lower cost
                        stimate a cost with AWS
                    noticia sobre custo
                        pesq e ver tem tem
                            senao deixa soh custo estimado de server mania
                    custo de ambiente
                    listar problemas de nao usar lib
                    custo de montar ambiente stg
                        replicar prd
                    custo operacional
                        ter equipe dedicada
                    total de trabalhos remotos
                    libs similares a testContainers
            extract info from oficial site
                done
                    funcionalidades
                    requisitos
            list main features
            plan sections
                list real problems
            review the sections
        criar projeto de exemplo
            done
                criar branch em openCertificate
                    test-containers-article
                criar config maven
                criar docker compose
                criar lista de tarefas
                configurar flyway no projeto
                criar classes de teste
                    container banco de dados
                        rodar migrations
                    initializer containers compartilhado
                    initializer container local
                    classe abstract test integracao
                configurar para usuario de invillia ter permisao no git
                    nao consigo fazer push no projeto
                commit all branch
                    push
                criar teste de integracao
                    banco
                        repository
                            insercao de dados
                            busca de dados
                        rest
                            create certificate
                                202
                                400
            TDD
                done
                    ajustar migrations com tdd
                        erro
                            done
                                nao roda flyway
                                    ajustar flyway fora do teste de integracao
                                Hibernate
                                    nao coloca snake case em colunas
                                        ERROR: column "dateended" of relation "event" does not exist
                                            Posição: 20
                                        solucao
                                            usar @Column para declarar colunas com snake case
                ajustar api
                    rg null 
                        alterar migration 
                            remover not null
                                deixar somente no dominio a regra
            melhorias
                assert
                    return zip file
                        validate
                            size
                            unzip
                                size pdfs
                                fisrt pdf size
            listar next tarefas
        write sections
            done
                intro
                lib
            uso
                container compartilhado
                container local
            conclusao
        lead magnet
            create link to calendly to articles
                a way to capture the email and create network
            put link to calendly in article
        next plans
            plan code
            plan the conclusion
            plan cronograma

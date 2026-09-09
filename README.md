# Monitor de Notícias — Windows

Reconstrução do código-fonte a partir da versão portátil estável **v4.0.2**, preparando a **v4.0.3**.

## Alterações v4.0.3 em desenvolvimento

- 18 termos padrão (8 existentes + 10 novos).
- Agenda independente para Notícias, Demandas e Vídeos.
- Cada agenda pode usar intervalo em minutos ou horários fixos.
- Proxy HTTP configurável com host padrão `proxy-7dn.mb:6060` e autenticação por usuário/senha.

## Estado da reconstrução

O pacote v4.0.2 disponível é binário (JAR/JPackage) e não contém os `.kt` originais. As classes de configuração acima são a primeira camada reconstruída. A interface Compose e os repositórios de busca da v4.0.2 ainda precisam ser portados para esta árvore antes que este projeto substitua o binário estável.

A v4.0.2 deve continuar sendo usada como referência estável até a validação completa da v4.0.3.

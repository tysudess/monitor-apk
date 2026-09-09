# Monitor de Notícias Android

Nova base de código-fonte do aplicativo Android **Monitor de Notícias**.

## Objetivo da v4.2.0

- reconstruir o aplicativo a partir de código-fonte versionado;
- aplicar o novo design mobile;
- evitar alterações diretas no APK compilado;
- gerar APK automaticamente pelo GitHub Actions;
- manter a v4.1.0 instalada e intocada durante os testes.

## Teste seguro

O build `debug` utiliza o sufixo `.dev`, portanto pode ser instalado ao lado do aplicativo estável.

## Build local

Requer JDK 17 e Gradle 8.9:

```bash
gradle :app:assembleDebug
```

O APK será criado em:

`app/build/outputs/apk/debug/app-debug.apk`

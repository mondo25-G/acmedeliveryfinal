open git bash here.
C:\Users\[χρήστης]\IdeaProjects\acmedeliveryfinal

Then run
git update-index --skip-worktree ./src/main/resources/application.yml

Then go to application.yml (skip profile functionality momentarily)
put your own hikari username/password/url


After initialization of the project, please replace the yaml file with these setting:

spring:
  jackson:
    serialization:
      fail-on-empty-beans: false
  data:
    jpa:
      repositories:
        bootstrap-mode: default
        enabled: true
  datasource:
    driver-class-name: oracle.jdbc.OracleDriver
    hikari:
      auto-commit: false
      connection-test-query: select 1 from dual
      connection-timeout: 10000
      idle-timeout: 30000
      max-lifetime: 60000
      maximum-pool-size: 10
      minimum-idle: 1
      pool-name: acmeDeliveryConnectionPooling
      transaction-isolation: TRANSACTION_READ_COMMITTED
      username: YOUR_USERNAME
      password: YOUR_PASSWORD
      data-source-properties:
        cachePrepStmts: true
        prepStmtCacheSize: 250
        prepStmtCacheSqlLimit: 2048
    type: com.zaxxer.hikari.HikariDataSource
    url: YOUR_DATABASE_URL
  jpa:
    generate-ddl: true
    hibernate:
      ddl-auto: create-drop #[RECOMMENDED FOR PRODUCTION PHASE ONLY]
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
      use-new-id-generator-mappings: true
    show-sql: true
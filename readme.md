# my simple GQL server

## lib version
 - [Ktor](https://github.com/ktorio/ktor) = 0.9.1
 - logback = 1.2.3
 - fuel = 1.12.1
 - kgraphql = 0.2.7
 - [Koin](https://insert-koin.io/) = 0.8.2
 - squash = 0.2.2
 - [H2](https://github.com/h2database/h2database) = 1.4.191 Embeddable database
 
coroutines "**enable**"

## gql
`{
  humans(size:1000) {
    id,
    inn,
    lastName,
    firsName,
    middleName
  }
}`

## links for read
https://github.com/pgutkowski/KGraphQL-NBA2012
http://adavis.info/2018/02/graphql-api-in-kotlin.html
https://github.com/adavis/ufo-sightings-api
https://www.prisma.io/blog/how-to-wrap-a-rest-api-with-graphql-8bf3fb17547d/

http://graphql-java.readthedocs.io/en/latest/schema.html
https://habr.com/post/326986/
https://github.com/howtographql/graphql-java

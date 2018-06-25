# my simple GQL server
## links for read
https://github.com/pgutkowski/KGraphQL-NBA2012
http://adavis.info/2018/02/graphql-api-in-kotlin.html
https://github.com/adavis/ufo-sightings-api
https://www.prisma.io/blog/how-to-wrap-a-rest-api-with-graphql-8bf3fb17547d/
http://graphql-java.readthedocs.io/en/latest/schema.html
https://habr.com/post/326986/

## lib version
  ktor_version = '0.9.1'
  logback_version = '1.2.3'
  fuel_version = '1.12.1'
  kgraphql_version = '0.2.7'
  koin_version = '0.8.2'
  squash_version = '0.2.2'
coroutines "enable"

##gql
{
  humans(size:1000) {
    id,
    inn,
    lastName,
    firsName,
    middleName
  }
}
FROM postgres:latest

ENV POSTGRES_USERNAME postgres

ENV POSTGRES_PASSWORD root

COPY initial-db initial-db

COPY restore.sh docker-entrypoint-initdb.d

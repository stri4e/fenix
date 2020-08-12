#!/bin/bash

set -e

host="$1"
port="$2"
shift 2
cmd="$@"

>&2 echo "container -> postgresdb for available!"

while ! (nc -z $host $port); do sleep 1; echo 'Waiting for postgresdb to start-up...'; done;

>&2 echo "postgresdb is up - executing command"

./$cmd

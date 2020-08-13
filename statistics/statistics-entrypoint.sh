#!/bin/bash

set -e

host="$1"
port="$2"
hostDiscovery="$3"
portDiscovery="$4"
shift 4
cmd="$@"

>&2 echo "container -> postgresdb and discovery for available!"

while ! (nc -z $host $port && nc -z $hostDiscovery $portDiscovery); do sleep 1; echo 'Waiting for postgresdb and discovery to start-up...'; done;

>&2 echo "postgresdb and discovery is up - executing command"

./$cmd

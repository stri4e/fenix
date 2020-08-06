#!/bin/bash

set -e

host="$1"
port="$2"
shift 2
cmd="$@"

>&2 echo "container -> config-server for available!"

while ! (nc -z $host $port); do sleep 1; echo 'Waiting for config-server services to start-up...'; done;

>&2 echo "config-server is up - executing command"

exec $cmd
#!/bin/bash

set -e

host="$1"
port="$2"
shift 4
cmd="$@"

>&2 echo "container -> discovery for available!"

while ! (nc -z $host $port ); do sleep 1; echo 'Waiting for discovery to start-up...'; done;

>&2 echo "discovery is up - executing command"

./$cmd

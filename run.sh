#!/bin/bash

function build_all_services() {
  if [ -f settings ]; then

    services=()
    while read line; do
      services+=("$line")
    done < settings

    for service in ${services[*]}
      do
          printf "RUN CLEAN AND BUILD SERVICE: %s\n" $service
          cd $service
          if [ -f gradlew ]; then
              ./gradlew clean && ./gradlew bootJar && cd ..
          fi
          if [ -f build-golang-app-script.sh ]; then
              /bin/bash build-golang-app-script.sh && cd ..
          fi
          printf "\n"
    done

  fi
}

function start_all() {
  build_all_services
  dc_main="$1"
  echo $dc_main
  docker-compose -f ${dc_main} up --build --force-recreate
}

function stop_all() {
  dc_main="$1"
  docker-compose -f ${dc_main} stop
  docker-compose -f ${dc_main} down --rmi all
}

action="build_all_services"

if [[ "$#" != "0"  ]]
then
    action=$@
fi

eval ${action}

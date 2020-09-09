#!/bin/bash

function build_all_services() {
  if [ -f build-settings ]; then

    cat build-settings
    services=$(jq -r '.services[]' build-settings)

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

function build_service() {
  service="$1"
  printf "RUN CLEAN AND BUILD SERVICE: %s\n" $service
  cd $service
  if [ -f gradlew ]; then
    ./gradlew clean && ./gradlew bootJar && cd ..
  fi
  if [ -f build-golang-app-script.sh ]; then
     /bin/bash build-golang-app-script.sh && cd ..
  fi
  printf "\n"
}

function start_all() {
  build_all_services
  dc_file="$1"
  project_name="$2"
  echo $dc_file
  docker-compose -p ${project_name} -f ${dc_file} up --build --force-recreate -d
}

function stop_all() {
  dc_file="$1"
  project_name="$2"
  docker-compose -p ${project_name} -f ${dc_file} stop
  docker-compose -p ${project_name} -f ${dc_file} down --rmi all
}

function restart() {
    stop_all
    start_all
}

function start() {
  dc_file="$1"
  service="$2"
  project_name="$3"
  printf "RUN CLEAN AND BUILD SERVICE: %s\n" $service
  cd $service
  if [ -f gradlew ]; then
    ./gradlew clean && ./gradlew bootJar && cd ..
  fi
  if [ -f build-golang-app-script.sh ]; then
     /bin/bash build-golang-app-script.sh && cd ..
  fi
  printf "\n"
  docker-compose -p ${project_name} -f ${dc_file} up --d --build --no-deps $service
}

function stop() {
  dc_file="$1"
  service="$2"
  project_name="$3"
  docker-compose -p ${project_name} -f ${dc_file} stop $service
  docker-compose -p ${project_name} -f ${dc_file} rm -f $service
}

function start_infrastructure() {
    dc_file="$1"
    service="$2"
    project_name="$3"
    docker-compose -p ${project_name} -f ${dc_file} up --d --build --no-deps $service
}

action="build_all_services"

if [[ "$#" != "0"  ]]
then
    action=$@
fi

eval ${action}

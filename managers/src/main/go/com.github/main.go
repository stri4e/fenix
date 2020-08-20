package main

import (
	"./container"
	_ "./docs"
	"./server"
)

// @title Managers API
// @version 1.0
// @description This is a sample service for managers
// @termsOfService http://swagger.io/terms/
// @license.name Apache 2.0
// @license.url http://www.apache.org/licenses/LICENSE-2.0.html
// @host localhost:8887
// @BasePath /
func main() {
	con := container.BuildContainer()
	err := con.Invoke(func(s *server.Server) {
		s.Run()
	})
	if err != nil {
		panic(err)
	}
}

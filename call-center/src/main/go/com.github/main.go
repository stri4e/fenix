package main

import (
	"./container"
	"./server"
)

func main() {
	con := container.BuildContainer()
	err := con.Invoke(func(s *server.Server) {
		s.Run()
	})
	if err != nil {
		panic(err)
	}
}

echo "Build Golang app start."

if [[ -d "build" ]]; then
	rm -Rf build;
fi

mkdir -p build/src/main

echo "Create build dir."

cp -r src/main/resources build/src/main

echo "Add configs folder."

echo "Download dependency start"

go mod download

echo "Download dependency end"

echo "Build Golang app start."

go build -o build/"${PWD##*/}" src/main/go/com.github/main.go

echo "Build Golang app end."
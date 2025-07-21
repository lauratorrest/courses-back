#!/bin/bash

set -e

echo "🛠️  Compilando la app con Gradle..."
./gradlew clean build -x test

echo "🐳 Construyendo imagen Docker..."
docker build -t courseya .

echo "🧹 Eliminando contenedor anterior si existe..."
docker rm -f courseya-container 2>/dev/null || true

echo "🚀 Ejecutando nuevo contenedor, corre en 8081 pero lo expongo en 9090"
docker run -d \
  -p 9090:8081 \
  --name courseya-container \
  --env-file .env \
  courseya

echo "✅ Listo. Visita: http://localhost:9090"

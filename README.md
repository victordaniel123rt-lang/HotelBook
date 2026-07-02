# HotelBook

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)

## 📋 Descripción

**HotelBook** es una aplicación backend desarrollada en Java para la gestión integral de reservaciones de hoteles. Proporciona una API REST completa que permite realizar operaciones de gestión de reservas, clientes, habitaciones y más.

## ✨ Características Principales

- ✅ Gestión de reservaciones de hoteles
- ✅ API REST robusta y escalable
- ✅ Documentación interactiva con Swagger
- ✅ Arquitectura modular basada en Spring Boot
- ✅ Base de datos relacional integrada

## 🚀 Inicio Rápido

### Requisitos Previos

- **Java 11** o superior
- **Maven 3.6** o superior
- Base de datos compatible (MySQL, PostgreSQL, etc.)

### Instalación

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/victordaniel123rt-lang/HotelBook.git
   cd HotelBook
   ```

2. **Compilar el proyecto:**
   ```bash
   mvn clean install
   ```

3. **Ejecutar la aplicación:**
   ```bash
   mvn spring-boot:run
   ```

La aplicación estará disponible en: `http://localhost:9091`

## 📚 Documentación API

Una vez que la aplicación esté en ejecución, accede a la documentación interactiva de Swagger en:

```
http://localhost:9091/swagger-ui/index.html
```

Aquí podrás explorar y probar todos los endpoints disponibles.

## 🏗️ Estructura del Proyecto

```
HotelBook/
├── src/
│   ├── main/
│   │   ├── java/          # Código fuente Java
│   │   └── resources/     # Archivos de configuración
│   └── test/              # Pruebas unitarias
├── pom.xml               # Configuración de Maven
└── README.md            # Este archivo
```

## 🛠️ Tecnologías Utilizadas

- **Java** - Lenguaje de programación principal
- **Spring Boot** - Framework para desarrollo web
- **Spring Data JPA** - Acceso a datos
- **Swagger/SpringFox** - Documentación de API
- **Maven** - Gestor de dependencias y construcción

## 📝 Configuración

La configuración de la aplicación se puede ajustar en el archivo `application.properties` o `application.yml`:

```properties
# Ejemplo de configuración
server.port=9091
spring.jpa.hibernate.ddl-auto=update
```

## 🤝 Contribución

Las contribuciones son bienvenidas. Por favor:

1. Fork el repositorio
2. Crea una rama para tu característica (`git checkout -b feature/mi-caracteristica`)
3. Commit tus cambios (`git commit -m 'Agrega mi característica'`)
4. Push a la rama (`git push origin feature/mi-caracteristica`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está disponible bajo una licencia open source. Consulta los términos de uso en el repositorio.

## 👨‍💻 Autor

Desarrollado por [victordaniel123rt-lang](https://github.com/victordaniel123rt-lang)

## 📞 Soporte

Para reportar problemas o sugerencias, abre un [issue](https://github.com/victordaniel123rt-lang/HotelBook/issues) en el repositorio.

---

⭐ Si te resulta útil, no olvides dejar una estrella en el repositorio.

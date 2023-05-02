# portfolio-BackEnd
Backend de proyecto portfolio, con Spring boot y JWT

# Descripción
Este proyecto proporciona una API REST que permite acceder y modificar información sobre mi portfolio (educacion, proyectos, experiencias). Está desarrollado en Java con el framework Spring Boot y utiliza MySQL como base de datos.

# Autenticación
Para acceder a los endpoints, es necesario autenticarse. El proyecto utiliza JWT para proporcionar tokens de autenticación. Para esto es necesario enviar una solicitud 'POST' a 'api/auth/login' con nombre de usuario y contraseña válidos.

# Tiempo de carga
Es importante tener en cuenta que el proyecto está alojado en 'render.com', y debido a que la plataforma tiene una política de suspensión de sus máquinas virtuales luego de 15 minutos de inactividad, esto provoca un tiempo de carga adicional de aproximadamente 5 minutos. Gracias por su comprensión.

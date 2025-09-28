Lista de requisitos actuales:
- [x] Req 1: Yo como docente necesito registrarme en el sistema de Gestión de Trabajos de grado
para iniciar el flujo de un proyecto de grado, comenzando con la presentación del
formato A. Contexto: Los datos que se deben ingresar son: nombres, apellidos, celular
(dato opcional), programa al que pertenece (Ingeniería de Sistemas, Ingeniería
Electrónica y Telecomunicaciones, Automática industrial, Tecnología en Telemática),
email institucional y contraseña (mínimo 6 caracteres, debe tener al menos un dígito,
al menos un carácter especial y al menos una mayúscula).
- [x] Req 2: Yo como docente necesito subir un el formato A para comenzar el proceso de proyecto
de grado. Contexto: el docente, una vez iniciada sesión, debe diligenciar un formulario
con los datos: Título del proyecto de grado, modalidad (investigación, práctica
profesional), fecha actual, director del proyecto de grado, codirector del proyecto de
grado, objetivo general, objetivos específicos, archivo PDF a adjuntar. Cuando se trata
de una modalidad de Práctica Profesional, el formato A debe tener al final, la carta de
aceptación de la empresa.
- [x] Req 3: Yo como coordinador de programa necesito evaluar un formato A para aprobar,
rechazar y dejar observaciones. Contexto: El sistema debe cargar al coordinador un
listado de proyectos y su estado. Una vez evaluado el formato A, el sistema debe
enviar un correo electrónico a los docentes y estudiantes implicados (se puede simular
el envío con un logger), informando que se hizo una evaluación.
- [ ] Req 4: Yo como docente necesito subir una nueva versión del formato A cuando hubo una
evaluación de rechazado para continuar con el proceso de proyecto de grado.
Contexto: el requisito se parece al requisito 2, solo que lleva el conteo del número del
intento (2,3). Después de un tercer intento, el proyecto es rechazado definitivamente y
el estudiante debe empezar un nuevo proyecto desde cero.
- [ ] Req 5: Yo como estudiante necesito entrar a la plataforma y ver el estado de mi proyecto de
grado. Contexto: los estados podrían ser algo como: en primera evaluación formato A,
en segunda evaluación formato A, en tercera evaluación formato A, aceptado formato
A y rechazado formato A.

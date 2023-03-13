# <AppClients>

## Descripción
Permite realizar operaciones relacionadas con clientes.
API rest desarrollada con Spring Boot 2.7.9 y Java 11.
Se conecta con una base de datos MySQL.

## /crearcliente
Endpoint de Entrada POST.
Permite crear un nuevo cliente, cargandolo en la base de datos.
# Parámetros de entrada:
List with details of similar products
- String nombre / Obligatorio
- String apellido / Obligatorio
- int edad / Obligatorio
- String fechaNacimiento / Obligatorio, formato dd/MM/yyyy o dd-MM-yyyy
- int id / Se genera automaticamente

## /kpideclientes
Endpoint de salida GET.
Retorna el promedio de edad entre todos los clientes y la desviación estándar entre las edades de todos los clientes.
# Parámetros de salida:
- String desviacionEstandar
- String promedioEdades 


## /listclients
Endpoint de salida GET.
Retorna una lista de personas con todos los datos y la fecha probable de muerte de cada una.
# Parámetros de salida:
- String nombre 
- String apellido 
- int edad 
- String fechaNacimiento
- int id 
- String fechaProbableMuerte
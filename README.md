# Taller TDD - Next Digital

Esta es la solución a la práctica de Test Driven Development propuesto por la empresa Next Digital durante los talleres de formación en la ETSII.

A continuación se documentarán los pasos tomados durante el desarrollo.

## Definición del problema base

En cada juego del set, cada uno de los dos jugadores puede tener una puntuación de 0, 15, 30 o 40.

En el caso de que ambos jugadores tengan una puntuación de 40, se encontrarían en "deuce". A partir de este punto, un jugador deberá anotar dos puntos seguidos para ganar el juego. Si esto no ocurre, volverán al "deuce".

El set lo ganará el primero que gane 4 juegos y al menos 2 juegos más que el oponente.

Se debe traducir la puntuación de cada juego usando "love", "fifteen", "thirty" y "forty" para 0, 15, 30 y 40, respectivamente. En el caso de 40-40, se traducirá como "deuce".

## Modelado del problema

### Modelado de un juego

Aunque en un juego las puntuaciones se muestren como 0, 15, 30 y 40, se usarán números enteros para representar el número de puntos anotados por cada jugador en el juego, partiendo desde el 0.

Por ejemplo, si el jugador 1 ha puntuado 15 veces, y el jugador 2 ha puntuado 16, esto se traduciría como "Ventaja para jugador 2". Si han puntuado 2 y 3 veces, se traduciría como 30 - 40, o "thirty"-"forty".

El juego terminará cuando uno de los dos jugadores tenga una puntuación superior a 40, y con una diferencia de dos con el otro jugador.

### Modelado de un set

Para ganar un set, es necesario ganar 4 juegos con diferencia de al menos 2.

Si nos fijamos en el modelado del juego, podemos observar que sigue el mismo comportamiento si usamos de nuevo números enteros para su representación. Ambos jugadores partirán con 0 juegos ganados. El set acabará en el momento en el que el jugador con mayor número de juegos ganados consiga >= 4 juegos, con una diferencia de 2 con el otro jugador.

## Test cases

### Juegos

Encontramos 5 tipos de combinación de puntuajes posibles:

- Los puntuajes son distintos, y el mayor es < 4: Se debe traducir cada puntuaje de manera literal
- Ambos puntuajes son iguales y < 3: Se debe traducir como "(traducción literal)-All"
- Ambos puntuajes son iguales y >= 3: Se debe traducir como "Deuce"
- Existe una diferencia de 1 unidad entre los dos puntuajes, y el mayor es >= 4: Se debe traducir como "Ventaja para (el jugador con mayor puntuaje)"
- Existe una diferencia igual a 2 unidades entre los dos puntuajes, y el mayor es >= 4: Se debe finalizar el juego

Y dos casos que no deberían darse en un flujo normal de partido:
- Existe una diferencia mayor a 2 unidades entre los dos puntuajes, y el mayor es >= 4: El juego ya debería haber finalizado (error)
- Alguno de los dos puntuajes es negativo: Se debe partir de 0 (error)

Con esto, se habrán comprobado todos los posibles casos.

### Sets

Podemos encontrar exactamente las mismas combinaciones en el caso de los sets. En este caso, no es necesaria una traducción, por lo que simplemente se definirá en qué ocasión debe acabar el set:

- Existe una diferencia igual a 2 unidades entre los dos puntuajes, y el mayor es >= 4: Se debe finalizar el set
- Mayor < 4, o diferencia < 2: El set debe continuar

Y se repiten de nuevo los casos que no deberían ocurrir:
- Existe una diferencia mayor a 2 unidades entre los dos puntuajes, y el mayor es >= 4: El juego ya debería haber finalizado (error)
- Alguno de los dos puntuajes es negativo: Se debe partir de 0 (error)

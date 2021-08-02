# Hands-On-MultiThreading-and-Streams

***This project is a simple back-end to simulate server provider systems
the main aim of the system is to manage multiple requests from multiple customers at the same time
and serve all the customer as fast as possible***

# requirements:
***the customer can rent a server of space 100 as maximum
more than one customer can rent the same server
same customer can rent spaces in multiple servers
the database will contain the already created servers
the customer can ask for server with specific space. Then if there was already created servers with 
enough space the system should allocate the requested space in the best fit server otherwise the 
system should span new server and allocate the required space on it.
Scenario suppose that I have a pool of three servers each has a free space of 30 Gigas. And then 
I was hit by simultaneous two requests in which each one of them needs 50 Gigas.***

# System Behavior :

***The system will create a thread for each request
the first request will enter and try to allocate 50 space.
the system will open new thread which will span new server with 50 remaining space (this will take 20 seconds ).
the second request will enter and try to allocate 50 space.
the system will know that there will be a server with 50 remaining capacity event if it's not ready yet.
the server will open new thread which will allocate the space in the already existed server(if the server wasn't ready yet the thread will wait until it's ready)***

# Applied Technologies

1- Java 

2- SpringBoot

3- Gradle


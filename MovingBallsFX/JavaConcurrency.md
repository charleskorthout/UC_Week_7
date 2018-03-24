# Java Concurrency (from Mastering concurreny in Java 8)

## Signaling
This design pattern explains how to implement the situation where a task has to notify an event to another task. 
The easiest way to implemnet this pattern is with a semaphore or a mutex, using the <code>ReentrantLock</code> or <code>Semaphore</code> classes of the Java language or er even the <code>wait()</code> and <code>notify()</code> methods included inthe <code>ObjectClass</code>.


``` java

public void task1() {
    section1();
    commonObject.notify();
}

public void task2() {
    commonObject.wait();
    section2();
}

```
Under these circumstances the <code>section2()</code> will always be excuted after the <code>section1()</code> method.

## Rendezvous

 This design pattern is a generalization of the Signaling pattern. In this case, the first task waits for an event of the second task and the second task waits for an event of the first task. 
 The solution is similar to that of the Signaling, but in this case you must use two objects instead of one.
 
 ``` java
 
 public void task1() {
    section1_1();
    commonObject1.notify();
    commonObject2.wait();
    section1_2();
 }
 
 public void task2() {
    section2_1();
    commonObject2.notify();
    commonObject1.wait();
    section2_2();
 }
 
 ```
 
 Under these circumstances, <code>section2_2()</code> allways will be executed after <code>section1_1()</code> and <code>section1_2()</code> after <code>section2_1()</code>. take into account that, if you put the call to the <code>wait()</code> before the call to the <code>notify()</code> method, you will have a deadlock.
 
 ## Mutex
 A mutex is a mechanism that you can use to implement a critical section ensuring mutal exclusion. 
 That is to say, only one task can execute the portion of code protected by the mutex at one time. In Java, you can implement a critical section using the <code>synchronized</code> keyword (that allows you to protect a portion of code or a full method), the <code>ReentrantLock</code> class, or the <code>Semaphore</code> class.
 
 ```java
 
public void task() {
	preCriticalSection();
	lockObject.lock(); // The critical section begins
	criticalSection();
	lockObject.unlock(); // The critical section ends
	postCriticalSection();
}
```
 
 ## Multiplex
 
 The Multiplex design pattern is a generalization of the mutex. In this case, a determined number of tasks can execute the critical section at once. 
 It is useful, for example, when you have multiple copies of a resource. The easiest way to implement this design pattern in Java is using the <code>Semaphore</code> class initiated to the number of tasks that can execute the critical section at once.
 
 ```java
public void task() {
	preCriticalSection();
	semaphoreObject.acquire(); // The critical section begins
	criticalSection();
	semaphoreObject.release(); // The critical section ends
	portCriticalSection();
}
```

## Barrier

The design pattern explains how to implement the situation where you need to sychronize some tasks at a common point. 
None of the tasks can continue with their execution untill all tasks have arrived at the synchronization point.

The Java concurrency API provides the <code>CyclicBarrier</code> class, which is an implementation of the design pattern.

```java

public void task() {
	preSyncPoint();
	barrierObject.await();
	postSyncPoint();
}

```
 

 
 
  




# Homework 2 - Theory Solutions

These solutions are meant for your own study purposes only. Please keep these solutions confidential and do not share them with anyone who is not currently taking the course with you. 

### Problem 1: Comparing Growth Rates 

Sorted left to right in increasing order. 

| *2/N* | *128* | *log N*  | *√N = N^(1/2)*  | *23N* | *N log N* | *N²*  | *42N³* | *2ⁿ*, *2ⁿ⁺¹* | *3ⁿ* | *N!* |
| ------| ------| -------- | --------------- | ----- | --------- | ----- | ------ | ------------ | ---- |----- |

The only two functions that grow at the same rate are *2ⁿ* and *2ⁿ⁺¹=2 x 2ⁿ*

### Problem 2: Running Time Analysis 


```
int sum = 0;
for (int i = 0; i < n; i++) 
    for(int k=i; k < n; k++) 
        sum++; // constant time
```
The inner loop will run *n* times for the first iteration of the outer loop, *n − 1* times for the
second iteration of the outer loop, etc. The total number of iterations for the inner loop is
therefore: *n + (n-1) + (n-2) + ... + 1*, i.e. the sum of integers from 1 to *n*. Using the sum formula for the arithmetic series, we can solve this sum as *n(n+1)/2 = ϴ(N²)*.

```
int sum = 0;
for (int i = 0; i < 23; i++) // 23 times
    for (int j = 0; j < n; j++) // n times
        sum++; // constant time
```

The inner loop will run *23n* times, which is *ϴ(n)*.

```
public int foo(int x,int k) {
    if (x <= k)
        return 1; // base case: constant time
    else
        return foo(x / k, k) + 1; // constant time for addition 
                                  // and return
}
```

Ignoring the recursion, any single run of `foo` takes constant time. We only need to find the total number of recursive calls. We assume that *x > k*. Since we are interested in Big-O running time, we can also assume that *x* is a power of *k*, i.e. *x = km*. In each step, we reduce *x* by a factor of *k*. Because you can do this *log x* times, the running time is *ϴ(log x)*.
Note that the base of the logarithm is irrelevant for big-O notation since *log_k x = (log₂ x) / (log₂ k) = (1/(log₂ k)) (log₂ x)*, and *(1/(log₂ k))* is a constant factor.


### Problem 3: Re-arranging Train Cars

![A shunting yard.](../2/figures/shunting.png)

**(a)**

* Let in, out are FIFO queues 
* Let s1, s2, s3 be LIFO stacks 

Operation           | in                    | out
--------------------|-----------------------|---------------------
(start)             | [5,8,1,7,4,2,9,6,3]   | []
s1.push(in.pop())   | [5,8,1,7,4,2,9,6]     | []
s2.push(in.pop())   | [5,8,1,7,4,2,9]       | []
s3.push(in.pop())   | [5,8,1,7,4,2]         | []
s1.push(in.pop())   | [5,8,1,7,4]           | []
s2.push(in.pop())   | [5,8,1,7]             | []
s3.push(in.pop())   | [5,8,1]               | []
out.push(in.pop())  | [5,8]                 | [1]
out.push(s1.pop())  | [5,8]                 | [2,1]
out.push(s1.pop())  | [5,8]                 | [3,2,1]
out.push(s2.pop())  | [5,8]                 | [4,3,2,1]
s1.push(in.pop())   | [5]                   | [4,3,2,1]
out.push(in.pop())  | []                    | [5,4,3,2,1]
out.push(s2.pop())  | []                    | [6,5,4,3,2,1]
out.push(s3.pop())  | []                    | [7,6,5,4,3,2,1]
out.push(s1.pop())  | []                    | [8,7,6,5,4,3,2,1]
out.push(s3.pop())  | []                    | [9,8,7,6,5,4,3,2,1]

**(b)**
Not all trains of length 9 can be re-arranged to [9, 8, 7, 6, 5, 4, 3, 2, 1] using 3 holding tracks. 
To show this, it is sufficient to provide a counter-example: [1, 9, 8, 7, 6, 5, 4, 3, 2].

Even though further explanation was not necessary for full credit, let's look at why this train cannot be re-arranged.

* Since we must push car 1 to the output track, we must push all other cars (9,
  8, ... , 2) to any of the holding tracks before we can pop car 1 from the
  input track.
* When pushing cars 2 to 9 we have to keep in mind that the holding
  tracks must have car numbers in descending order from top to bottom, since we
  are only allowed to push them onto the output track in ascending order.
  Example: after pushing all cars, car 2 must be at the top of its
  corresponding stack, since it is the next car to be pushed onto the output
  track.
* This, however, is impossible, since we are only given 3 tracks. Say that we
  push car 2 onto holding track s1, car 3 onto holding track s2, and car 4 onto
  holding track s3. By the time we get to car 5, we will have to push it on top of a
  smaller-valued car (i.e., car 2, 3, or 4). This will be the case regardless
  of which holding tracks we use to hold cars 2, 3, and 4.

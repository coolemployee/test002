test002
=======

Test 002 for Implemica

Runnable jar can be downloaded here: https://www.dropbox.com/s/lu2kwuyr3g5y5ji/Test002.jar
Please, provide path/filename as command-line argument.


You are given a list of cities. Each direct connection between two cities has its transportation cost (an integer bigger than 0). The goal is to find the paths of minimum cost between pairs of cities. Assume that the cost of each path (which is the sum of costs of all direct connections belongning to this path) is at most 200000. The name of a city is a string containing characters a,...,z and is at most 10 characters long.2) 

Input
s [the number of tests <= 10]
n [the number of cities <= 10000]
NAME [city name]
p [the number of neighbours of city NAME]
nr cost [nr - index of a city connected to NAME (the index of the first city is 1)]
[cost - the transportation cost]
r [the number of paths to find <= 100]
NAME1 NAME2 [NAME1 - source, NAME2 - destination]
[empty line separating the tests]

Output
cost [the minimum transportation cost from city NAME1 to city NAME2 (one per line)]

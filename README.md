
## Junction Simulator
This Java app simulates car traffic at a junction controlled by traffic lights. Usage:

`./run.sh <input_file> <output_file>`

By default, the simulation is continued until no cars are left. This can be switched by including the `-f` option.

### Assumptions:
- in a single step, at most one car from a single direction will drive through the junction,
- cars avoid collisions,
- cars drive at yellow lights.


### Notes:
- the simulation starts with South/North lights GREEN, and East/West lights RED,
- it takes 2 steps for lights to switch from RED to GREEN and vice versa, therefore if the simulation states that it queues a light to be green for `n` steps, only `n-2` cars will pass,
- if the simulation queues a light for 0 steps, no changes occur,
- the number of steps to queue each light is chosen based on how many cars are in the queue (`2+min(max(3, cars / 3), 20)`).


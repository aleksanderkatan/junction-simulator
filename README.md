
## Junction Simulator
This Java app simulates car traffic at a junction controlled by traffic lights. Usage:

`./run.sh <input_file> <output_file>`

By default, the simulation is continued until no cars are left. This can be switched by including the `-f` option.

### Assumptions:
- in a single step, at most one car from a single direction will drive through the junction,
- cars avoid collisions,
- cars drive at yellow lights.


### Notes:
- addVehicle and step are preformed in O(1) time,
- the simulation starts with South/North lights GREEN, and East/West lights RED,
- it takes 2 steps for lights to switch from RED to GREEN and vice versa, therefore if the simulation states that it queues a light to be GREEN for `n` steps, only up to `n-2` cars may pass,
- if there are no cars on either N/S or E/W line, that line is enqueued for 0 steps (no color change occurs),
- the number of steps to queue each light is chosen based on how many cars are in the queue (`2+min(max(3, cars / 3), 20)`).


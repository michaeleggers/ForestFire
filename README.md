# ForestFire
Simulates the distribution of a forest fire.

***The Simulation Creator***
On program startup you can specify the parameters of the simulation. All parameters with a leading "p" are probability floating point values ranging from [0, 1].

**Explanation of the parameters:**

| Parameter | Description |
| --- | --- |
| `cycles` | duration of the simulation |
| `pInitFire` | percentage of the whole forest to be on fire at the beginning of the simulation |
| `pIgnite` | likelyhood of a already lit tree to ignite another tree next to it |
| `pZeus` | likelyhood of a thunderbolt hitting the forest |
| `max life cycles` | for how long a tree will burn |
| `wind direction` | direction the wind is blowing to |
| `wind force` | strength of the wind |
| `forest size (x/y)` | size of the forest |
| `pInitTrees` | how many fields of the forest are actual trees (and not mud) at the beginning of the sim. |

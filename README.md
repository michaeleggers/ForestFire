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

**Watching the simulation**
Once entered all the parameters the program will start the simulation with a click on `create simulation`. You then can cycle through the animation with `previous` and `next`. Enjoy!

**Example of a simulation process:**

*Entering the parameters:*

![sim_creator](https://cloud.githubusercontent.com/assets/11651836/24853866/c0e864aa-1ddc-11e7-891c-0f6b45d12a4f.PNG)

*Initial state of the simulation:*

![state1](https://cloud.githubusercontent.com/assets/11651836/24854072/5560a43a-1ddd-11e7-989e-c844c01bf4bd.PNG)

*After a while...*

![state2](https://cloud.githubusercontent.com/assets/11651836/24854073/55613314-1ddd-11e7-9998-886661b1081e.PNG)

*A few cycles later:*

![state3](https://cloud.githubusercontent.com/assets/11651836/24854071/555ddf3e-1ddd-11e7-9528-219d72de0256.PNG)

*And finally more than half of the trees are gone :(*

![state4](https://cloud.githubusercontent.com/assets/11651836/24854070/555c0d9e-1ddd-11e7-8144-e108bf14c08c.PNG)

***Todos***
- display the actual cycle-number.


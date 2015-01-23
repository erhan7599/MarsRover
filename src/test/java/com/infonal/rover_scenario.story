Narrative: In order to explore Mars
    As NASA
    I want to send commands to rovers on the ground

Scenario:Lander is in orbit, lands rovers to the planet, and acts as a relay

Given a lander
Given a plateau with dimensions 5, 5
Given a rover at position <position_x>, <position_y>, <heading>
When lander is commanded as <command>
Then rover should end up at <end_position_x>, <end_position_y>, <end_heading>

Examples:
|position_x|position_y|heading|command    |end_position_x|end_position_y|end_heading|
|1         |2         |N      |LMLMLMLMM  |1             |3             |N          |
|3         |3         |E      |MMRMMRMRRM |5             |1             |E          |
using UnityEngine;
using System.Collections;
/*
Author: Joshua Collaco
Last Modified: Joshua Collaco
Last Modified Date: OCT/4/2015
Program Description: Controller for enemy unit
*/

public class enemyScript : MonoBehaviour {

    public int direction;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
    // Movement of the enemies 
	void Update () {
        Vector2 enemyKiller = this.gameObject.GetComponent<Transform>().position;

        if (enemyKiller.y > -350)
        {

            if (direction % 2 == 0)
            {
                enemyKiller.x += 2f;
            }
            else if (direction % 2 == 1)
            {
                enemyKiller.x -= 2f;
            }

            enemyKiller.y -= 10;
            this.gameObject.GetComponent<Transform>().position = enemyKiller;

        }
        else if (enemyKiller.y <= -350)
        {
            direction = Random.Range(1, 20);
            enemyKiller.x = Random.Range(-210,210);
            enemyKiller.y = 410;
            this.gameObject.GetComponent<Transform>().position = enemyKiller;
        }
    }
}

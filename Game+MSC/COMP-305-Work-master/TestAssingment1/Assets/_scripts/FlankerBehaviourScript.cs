using UnityEngine;
using System.Collections;
/*
Author: Joshua Collaco
Last Modified: Joshua Collaco
Last Modified Date: OCT/4/2015
Program Description: Controller for player
*/

public class FlankerBehaviourScript : MonoBehaviour {

    public GameObject flanker;


	// Use this for initialization
	void Start () {
        flanker = this.gameObject;
        


	}
	
	// Update is called once per frame
	void Update () {
        this.movement();
    }

    // Allows for movement of the player
    public void movement()
    {
        Vector2 direction = flanker.GetComponent<Transform>().position;
        
        if (Input.GetAxis("Horizontal") < 0 && direction.x - 10 != -310)
        {
            direction.x -= 10;
        }

        if (Input.GetAxis("Horizontal") > 0 && direction.x + 10 != 310)
        {
            direction.x += 10;
        }

        if (Input.GetAxis("Vertical") < 0 && direction.y - 10 != -220)
        {
            direction.y -= 5;
        }

        if (Input.GetAxis("Vertical") > 0 && direction.y + 10 != 220)
        {
            direction.y += 5;
        }


        flanker.GetComponent<Transform>().position = direction;
    }
}

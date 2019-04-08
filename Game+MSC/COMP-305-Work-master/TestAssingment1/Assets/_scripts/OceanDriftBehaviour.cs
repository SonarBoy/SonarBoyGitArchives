using UnityEngine;
using System.Collections;
/*
Author: Joshua Collaco
Last Modified: Joshua Collaco
Last Modified Date: OCT/4/2015
Program Description: Controller for ocean and drift
*/

public class OceanDriftBehaviour : MonoBehaviour {

    public int direction;
    

	// Use this for initialization
	void Start () {
        direction = Random.Range(1,20);
	}
	
	// Update is called once per frame
    //Updating the ocean object DRIFT!!
	void Update () {
        Vector2 oceanVector = this.gameObject.GetComponent<Transform>().position;

        if (oceanVector.y > -350)
        {

            if(direction % 2 == 0)
            {
                oceanVector.x += 0.1f;
            }else if (direction % 2 == 1)
            {
                oceanVector.x -= 0.1f;
            }

            oceanVector.y -= 5;
            this.gameObject.GetComponent<Transform>().position = oceanVector;

        }else if (oceanVector.y <= -350)
        {
            direction = Random.Range(1, 20);
            oceanVector.x = 0;
            oceanVector.y = 410;
            this.gameObject.GetComponent<Transform>().position = oceanVector;
        }
	
	}
}

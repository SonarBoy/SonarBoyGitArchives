using UnityEngine;
using System.Collections;
/*
Author: Joshua Collaco
Last Modified: Joshua Collaco
Last Modified Date: OCT/4/2015
Program Description: Controller for Paratrooper 
*/
public class ParaGuyScript : MonoBehaviour {

	
	void Start () {
	
	}
	
	// Update is called once per frame
    //Drift for the para guy
	void Update () {
        Vector2 paraPosition = this.GetComponent<Transform>().position;
        

        if (paraPosition.y > -310)
        {
            paraPosition.y -= 5;
            this.GetComponent<Transform>().position = paraPosition;
        }else if(paraPosition.y <= -310)
        {
            paraPosition.x = Random.RandomRange(-310f,310f);
            paraPosition.y = 800;
            this.enabled = true;
            this.GetComponent<Transform>().position = paraPosition;
        }
        else
        {

        }


	
	}
}

using UnityEngine;
using System.Collections;
/*
Author: Joshua Collaco
Last Modified: Joshua Collaco
Last Modified Date: OCT/4/2015
Program Description: Generator for multiple enemies on startup
*/

public class EnemyGenerator : MonoBehaviour {
    public GameObject killers;


	// Use this for initialization
    // Generate of all the enemies
	void Start () {
	    for(int runner = 0; runner < 4; runner++)
        {
            Instantiate(killers);
        }
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}

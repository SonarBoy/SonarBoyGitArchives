using UnityEngine;
using System.Collections;
using UnityEngine.UI;
/*
Author: Joshua Collaco
Last Modified: Joshua Collaco
Last Modified Date: OCT/4/2015
Program Description: Collider for player 
*/

public class FlankerCollider : MonoBehaviour {
    public Text livesObject;
    public Text scoreObject;

    public Text gameOver;
    public Text finalScore;


    private AudioSource[] sources;
    private int lives = 6;
    private int score = 0;

    public GameObject cam;

	// Use this for initialization
	void Start () {
        this.sources = this.GetComponents<AudioSource>();
        this.livesObject.text = "LIVES: " + lives;
        this.scoreObject.text = "SCORE: " + score;
	}
	
	// Update is called once per frame
	void Update () {
	
	}

    // Checking for collisions for the paratrooper and enemy
    void OnTriggerEnter2D(Collider2D bot)
    {
        if (bot.tag == "EnemyPlane") {
            Debug.Log("You Got hit by the enemy");
            this.sources[0].Play();

            this.lives--;

            // if your lives are less than 0 the game is then over
            if (this.lives <= 0)
            {
                this.gameOver.text = "GAME OVER";
                this.finalScore.text = this.scoreObject.text;
                this.scoreObject.enabled = false;
                this.livesObject.enabled = false;
                Destroy(gameObject);

                this.cam = GameObject.Find("Main Camera");
                this.cam.GetComponent<AudioSource>().Stop();
                
            }
            
            this.livesObject.text = "LIVES: " + this.lives;

            

        }else if(bot.tag == "ParaGuy"){
            Debug.Log("Pick up the para guy");
            this.sources[1].Play();
            this.enabled = false;

            this.score += 100;
            this.scoreObject.text = "SCORE: " + this.score;

        }
        else
        {

        }
    }
}

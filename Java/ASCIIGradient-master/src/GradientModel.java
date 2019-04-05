import javax.swing.JOptionPane;

public class GradientModel {

	private int width;
	private int length;
	private String[][] canvas;
	
	//First Line
	private double slope;
	private double b;
	private int [] pointX;
	private int [] pointY;
	
	//Second Line
	private double inverseSlope;
	private double inverseB;
	private double inverseX;
	private double inverseY;
	
	//Triangle Length
	private int triangleLength;
	
	private LineModel topBorder;
	private LineModel bottomBorder;
	
	public void setWidth(int w){
		this.width = w;
	}
	
	public void setLength(int l){
		this.length = l;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getLength(){
		return this.length;
	}
	
	public GradientModel(int wid,int len){
		this.width = wid;
		this.length = len;
		this.pointX = new int[2];
		this.pointY = new int[2];
		this.topBorder = null;
		this.bottomBorder = null;
		
		this.canvas = new String[wid][len];
		blankFill();
	}
	
	public void initialize(int wid,int len){
		this.width = wid;
		this.length = len;
		this.pointX = new int[2];
		this.pointY = new int[2];
		this.slope = 0;
		this.b = 0;
		this.inverseSlope = 0;
		this.inverseB = 0;
		this.inverseX = 0;
		this.inverseY = 0;
		this.topBorder = null;
		this.bottomBorder = null;
		
		this.canvas = new String[wid][len];
		blankFill();
	}
	
	private void blankFill(){
		
		int widthArray = this.canvas[0].length;
		int lengthArray = this.canvas.length;
		
		String [][] replacement = new String[widthArray][lengthArray];
		
		for(int widthRunner = 0;widthRunner < widthArray;widthRunner++){
			for(int lengthRunner = 0; lengthRunner < lengthArray;lengthRunner++){
				replacement[widthRunner][lengthRunner] = " ";
			}
		}
		
		this.canvas = null;
		this.canvas = replacement;
	}
	
	public String showCanvas(){
		
		int widthArray = this.canvas[0].length;
		int lengthArray = this.canvas.length;
		
		String out = "";
		
		for(int widthRunner = 0;widthRunner < widthArray;widthRunner++){
			for(int lengthRunner = 0; lengthRunner < lengthArray;lengthRunner++){
				if(widthRunner == 0){
					System.out.print(lengthRunner + " ");
				}
				
				if(widthRunner == 1 && lengthRunner == 0){
					System.out.println();
				}
				
				out += this.canvas[widthRunner][lengthRunner] + "     ";
			}
			
			out += "\n";
		}
		
		return out;
	}
	
	public void drawPointsAt(double xOne, double yOne, double xTwo, double yTwo){
		
		this.pointX[0] = (int)xOne;
		this.pointX[1] = (int)yOne;
		this.pointY[0] = (int)xTwo;
		this.pointY[1] = (int)yTwo;
		
		int perpY = 0;
		int perpX = this.calculateTriangle(xOne, yOne, xTwo, yTwo);
		triangleLength = this.calculateTriangle(xOne, yOne, xTwo, yTwo);
		
		try {
			//Solving for the first line
			this.slope = (double)((yTwo - yOne)/(xTwo - xOne));
			this.b = (yTwo - (this.slope * xTwo)); //Changed here
			
			//Solving for the second line
			this.inverseSlope = (-1/this.slope);
			this.inverseX = (xOne * this.inverseSlope);
			this.inverseB = (this.inverseX - yOne);
			//perpY = (int)((this.inverseSlope * (perpX)) + this.b);
			
			Thread.sleep(200);
			
			//Print Out
			System.out.println("Slope: " + this.slope);
			System.out.println("B value: " + this.b);
			System.out.println("Inverse Slope: "+this.inverseSlope);
			
			//Corner of the Rectangle
			this.canvas[(int)xTwo][(int)yOne] = "X";
			
			this.getIntercepts(this.slope, this.inverseSlope);
			
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
		
	}
	
	private int calculateTriangle(double xOne, double yOne, double xTwo, double yTwo){
		
		int a = (int) Math.abs(xTwo - xOne);
		int b = (int) Math.abs(yTwo - yOne);
		
		int aSquared = (int) Math.pow(a, 2);
		int bSquared = (int) Math.pow(b, 2);
		
		int c = (int) Math.sqrt(aSquared + bSquared);
		
		return c;
	}
	
	private void getIntercepts(double firstLineSlope, double secondLineSlope){
		
		int perp = 1;
		int serp = 1;
		int derp = 1;
		int bValue = 0;
		int secondBValue = 0;
		
		int middleGradientLine = 0;

		System.out.println("Length: " + triangleLength);
		
		
		//First Line Negative
		if(firstLineSlope < 0){
			
			System.out.println("Second Slope positive");

			bValue = (int)(this.pointX[1] - (this.inverseSlope * this.pointX[0]));
			secondBValue = (int)(this.pointY[1] - (this.inverseSlope * this.pointY[0]));
			System.out.println("NEGATIVE SLOPE: B VALUE ON THE SECOND: "+bValue);
			
			for(int runner = 0; runner <= 50; runner++){
				perp = (int)((firstLineSlope * (runner)) +this.b);
				serp = (int)((this.inverseSlope * (runner)) + bValue);
				derp = (int)((this.inverseSlope * (runner)) + secondBValue);
				
				if(perp >= 0 && perp < 51){
					
					if((runner < pointX[0] && runner > pointY[0])){
						this.canvas[runner][perp] = "*";
					}else if(runner > pointX[0] && runner < pointY[0]){
						this.canvas[runner][perp] = "*";
					}else{
						this.canvas[runner][perp] = " ";
					}
				}
				
				if(serp >= 0 && serp < 51){
					this.canvas[runner][serp] = ".";
				}
				
				if(derp >= 0 && derp < 51){
					this.canvas[runner][derp] = "0";
				}
				
				
				
			}
			
			
			this.outerBorders(this.inverseSlope, bValue, 0,".");
			this.outerBorders(this.inverseSlope, secondBValue, 1,"0");
			
		//When Second Line is Negative	
		}else if(secondLineSlope < 0){
			
			System.out.println("First Slope positive");
			
			bValue = (int)(this.pointX[1] - (secondLineSlope * this.pointX[0]));
			secondBValue = (int)(this.pointY[1] - (this.inverseSlope * this.pointY[0]));
			System.out.println("POSITIVE SLOPE: B VALUE ON THE SECOND: "+bValue);
			
			
			for(int runner = 0 ,runnerTwo = 50; runner <= 50; runner++,runnerTwo--){
				perp = (int)((firstLineSlope * (runner)) + this.b);
				serp = (int)((this.inverseSlope * (runner)) + bValue);
				derp = (int)((this.inverseSlope * (runner)) + secondBValue);
				
				if(perp >= 0 && perp < 51){
					
					
					
					if((runner < pointX[0] && runner > pointY[0])){
						this.canvas[runner][perp] = "*";
					}else if(runner > pointX[0] && runner < pointY[0]){
						this.canvas[runner][perp] = "*";
					}else{
						this.canvas[runner][perp] = " ";
					}
					
				}
				
				if(serp >= 0 && serp < 51){
					this.canvas[runner][serp] = ".";
				}
				
				if(derp >= 0 && derp < 51){
					this.canvas[runner][derp] = "0";
				}
				
				
			
			}
			this.outerBorders(this.inverseSlope, bValue, 1,".");
			this.outerBorders(this.inverseSlope, secondBValue, 0,"0");
		}
		
		
		this.topBorder = new LineModel(this.inverseSlope, bValue);
		this.bottomBorder = new LineModel(this.inverseSlope,secondBValue);
		
		return;
	}
	
	private void outerBorders(double slope, double bValue, int upOrDown, String sign){
		
		int holder;
		
		if(upOrDown == 0){
			
			int count = 0;
			
			for(int value = (int) bValue; value  >= (bValue - 30);value--){
				
				count++;
				
				for(int runner = 0; runner <= 50; runner++){
					
					holder = (int)((this.inverseSlope * (runner)) + (bValue - count));
					
					if(holder >= 0 && holder < 51){
						this.canvas[runner][holder] = sign;
					}
					
				}
			}
			
		}else if(upOrDown == 1){
			
			int count = 0;
			
			for(int value = (int) bValue; value  <= (bValue + 30);value++){
				
				count++;
				
				for(int runner = 0; runner <= 50; runner++){
					
					holder = (int)((this.inverseSlope * (runner)) + (bValue + count));
					
					if(holder >= 0 && holder < 51){
						this.canvas[runner][holder] = sign;
					}
					
				}
			}
		}
		
		
	}
	
	
	public String[] lines(){
		String[] borders = {this.topBorder.toString(),this.bottomBorder.toString()};
		return borders;
	}
	
}

import bpy
import json

from pprint import pprint

with open('C:\\test\\strings.json') as data_file:    
    data = json.load(data_file)
pprint(data)

#Define vertices and faces (replace these test values with input arrays later, this code will create a cube)
verts = [(data.pointx,data.pointy,data.pointz)]
 
#Define mesh and object
mesh = bpy.data.meshes.new("Cube")
object = bpy.data.objects.new("Cube", mesh)
 
#Set location and scene of object
object.location = bpy.context.scene.cursor_location
bpy.context.scene.objects.link(object)
 
#Create mesh
mesh.from_pydata(verts,[],[])
mesh.update(calc_edges=True)
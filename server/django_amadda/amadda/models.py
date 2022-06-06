from ast import Str
from django.db import models
from django.contrib.postgres.fields import ArrayField  

class Reserveclass(models.Model):
    student_id = models.PositiveSmallIntegerField(default=0) 
    class_num = models.PositiveSmallIntegerField(default=0)
    class_type = models.PositiveSmallIntegerField(default=0)
    class_select = models.PositiveSmallIntegerField(default=0)
    class_title = models.CharField(max_length=100, default= '0')
    class_object = models.CharField(max_length=50, default= '0')
    class_loud = models.CharField(max_length=50,default='0')
    class_teacher = models.CharField(max_length=100, default='0')
    
    def __str__(self):
        return str(self.student_id)

class teacher(models.Model) :
    classroom = models.CharField(max_length=20, default='0', primary_key=True)
    teacher = ArrayField(models.CharField(max_length=20), blank=True) 

    def __str__(self) :
        return self.classroom 
    
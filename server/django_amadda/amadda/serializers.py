from dataclasses import fields
from rest_framework import serializers
from amadda.models import Reserveclass
from amadda.models import teacher

class ReserveSerializer(serializers.ModelSerializer):
    class Meta:
        model = Reserveclass
        fields = (
            'student_id',
            'class_num',
            'class_type',
            'class_select',
            'class_title',
            'class_object',
            'class_loud',
            'id'
            
            
            
        )
        

class TeacherSerializer(serializers.ModelSerializer) :
    class Meta :
        model = teacher
        fields = (
            'classroom', 
            'teacher'
        )
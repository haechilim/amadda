from django.shortcuts import render

# Create your views here.
from rest_framework import viewsets


from amadda.models import Reserveclass
from amadda.serializers import ReserveSerializer

from amadda.models import teacher
from amadda.serializers import TeacherSerializer


class ReserveViewSet(viewsets.ModelViewSet):
    queryset = Reserveclass.objects.all()
    serializer_class = ReserveSerializer

class TeacherViewSet(viewsets.ModelViewSet) :
    queryset = teacher.objects.all()
    def get_serializer_class(self):
        return TeacherSerializer
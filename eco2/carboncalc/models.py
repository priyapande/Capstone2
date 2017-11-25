from django.db import models
import uuid
from django.core.validators import RegexValidator
# Create your models here.


class User_Main(models.Model):
    uid = models.UUIDField(primary_key=True, editable=False, default=uuid.uuid1)
    name = models.CharField(max_length=80, blank=False)
    email = models.CharField(max_length=50, blank=False,
                             validators=[RegexValidator('(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$)', message='invalid e-mail')])
    password = models.CharField(max_length=50, blank=False, null=False)
    created_on = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return self.name

    class Type(object):
        MARUTI_SUZUKI = 'MS'
        TOYOTA = 'TY'
        HYUNDAI = 'HY'
        HONDA = 'HON'
        FIAT = 'FT'
        VOLKSWAGEN = 'VG'
        FORD = 'FD'
        LUXURY = 'LX'
        OTHERS = 'OTH'
        CHOICES = (
            (MARUTI_SUZUKI, 'MARUTI_SUZUKI'),
            (TOYOTA, 'TOYOTA'),
            (HYUNDAI, 'HYUNDAI'),
            (HONDA, 'HONDA'),
            (FIAT, 'FIAT'),
            (VOLKSWAGEN, 'VOLKSWAGEN'),
            (FORD, 'FORD'),
            (LUXURY, 'LUXURY'),
            (OTHERS, 'OTHERS')
        )

    class Meta:
        ordering = ["-created_on"]

    car_company = models.CharField(choices=Type.CHOICES, default=Type.OTHERS, db_index=True, max_length=50)
    car_model = models.CharField(max_length=4, blank=True, validators=[RegexValidator('(^[1-9]+[0-9]+$)')])




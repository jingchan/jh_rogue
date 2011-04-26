# encoding: utf-8
import datetime
from south.db import db
from south.v2 import SchemaMigration
from django.db import models

class Migration(SchemaMigration):

    def forwards(self, orm):
        
        # Adding field 'Mob.asset'
        db.add_column('mob_mob', 'asset', self.gf('django.db.models.fields.files.ImageField')(default='', max_length=100), keep_default=False)


    def backwards(self, orm):
        
        # Deleting field 'Mob.asset'
        db.delete_column('mob_mob', 'asset')


    models = {
        'mob.mob': {
            'Meta': {'object_name': 'Mob'},
            'asset': ('django.db.models.fields.files.ImageField', [], {'max_length': '100'}),
            'description': ('django.db.models.fields.TextField', [], {}),
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'name': ('django.db.models.fields.TextField', [], {}),
            'type': ('django.db.models.fields.related.ForeignKey', [], {'to': "orm['mob.MobType']"})
        },
        'mob.mobtype': {
            'Meta': {'object_name': 'MobType'},
            'description': ('django.db.models.fields.TextField', [], {}),
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'name': ('django.db.models.fields.TextField', [], {})
        }
    }

    complete_apps = ['mob']

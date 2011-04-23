# encoding: utf-8
import datetime
from south.db import db
from south.v2 import SchemaMigration
from django.db import models

class Migration(SchemaMigration):

    def forwards(self, orm):
        
        # Adding model 'TileTexture'
        db.create_table('map_tiletexture', (
            ('id', self.gf('django.db.models.fields.AutoField')(primary_key=True)),
            ('name', self.gf('django.db.models.fields.TextField')()),
            ('description', self.gf('django.db.models.fields.TextField')()),
            ('asset', self.gf('django.db.models.fields.files.ImageField')(max_length=100)),
        ))
        db.send_create_signal('map', ['TileTexture'])

        # Adding model 'Map'
        db.create_table('map_map', (
            ('id', self.gf('django.db.models.fields.AutoField')(primary_key=True)),
            ('tiles', self.gf('django.db.models.fields.TextField')()),
        ))
        db.send_create_signal('map', ['Map'])


    def backwards(self, orm):
        
        # Deleting model 'TileTexture'
        db.delete_table('map_tiletexture')

        # Deleting model 'Map'
        db.delete_table('map_map')


    models = {
        'map.map': {
            'Meta': {'object_name': 'Map'},
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'tiles': ('django.db.models.fields.TextField', [], {})
        },
        'map.tiletexture': {
            'Meta': {'object_name': 'TileTexture'},
            'asset': ('django.db.models.fields.files.ImageField', [], {'max_length': '100'}),
            'description': ('django.db.models.fields.TextField', [], {}),
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'name': ('django.db.models.fields.TextField', [], {})
        }
    }

    complete_apps = ['map']

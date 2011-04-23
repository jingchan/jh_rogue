# encoding: utf-8
import datetime
from south.db import db
from south.v2 import SchemaMigration
from django.db import models

class Migration(SchemaMigration):

    def forwards(self, orm):
        
        # Adding model 'MobType'
        db.create_table('mob_mobtype', (
            ('id', self.gf('django.db.models.fields.AutoField')(primary_key=True)),
            ('name', self.gf('django.db.models.fields.TextField')()),
            ('description', self.gf('django.db.models.fields.TextField')()),
        ))
        db.send_create_signal('mob', ['MobType'])

        # Adding model 'Mob'
        db.create_table('mob_mob', (
            ('id', self.gf('django.db.models.fields.AutoField')(primary_key=True)),
            ('name', self.gf('django.db.models.fields.TextField')()),
            ('description', self.gf('django.db.models.fields.TextField')()),
            ('type', self.gf('django.db.models.fields.related.ForeignKey')(to=orm['mob.MobType'])),
        ))
        db.send_create_signal('mob', ['Mob'])


    def backwards(self, orm):
        
        # Deleting model 'MobType'
        db.delete_table('mob_mobtype')

        # Deleting model 'Mob'
        db.delete_table('mob_mob')


    models = {
        'mob.mob': {
            'Meta': {'object_name': 'Mob'},
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

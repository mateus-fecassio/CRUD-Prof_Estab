import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'profissionais' },
  {
    path: 'profissionais',
    loadChildren: () => import('./pages/profissionais/profissionais.module').then((m) => m.ProfissionaisModule),
  },
  {
    path: 'estabelecimentos',
    loadChildren: () => import('./pages/estabelecimentos/estabelecimentos.module').then((m) => m.EstabelecimentosModule),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
